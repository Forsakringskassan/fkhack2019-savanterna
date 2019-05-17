import * as React from "react";

import { Form } from "semantic-ui-react";
import RestService from "../../rest/rest-service";
import RestInterface from "../../rest/rest-interface";

interface Props {
}

interface State {
    gruppnamn: string;
    granskare: RestInterface.User;
    kategorier: Array<RestInterface.Kategori>
    beskrivning: string;
    kategoriOptions: Array<RestInterface.dropdownItem>;
}

export class AdminSkapaBehorighet extends React.Component<Props, State> {

    constructor(props: any){
        super(props);
        this.state = {
            gruppnamn: "",
            granskare: {
                id: "1",
                namn: "Per Persson"
            },
            kategorier: [],
            beskrivning: "",
            kategoriOptions: [],
        };
        this.getKategorier();
    }

    getKategorier(){
        let self = this;
        RestService.hamtaKategorier()
        .then((response) => {
            if (response.ok) {
            return response.json();
            } else {
            throw new Error('Something went wrong');
            }
        })
        .then((results : Array<RestInterface.Kategori>) => {
            let kategoriOptions : Array<RestInterface.dropdownItem> = [];
            for(let i in results){
                kategoriOptions.push({
                    key: results[i].id,
                    text: results[i].namn,
                    value: results[i].id
                });
            }

            self.setState({
                kategoriOptions: kategoriOptions,
            });
        })
        .catch((error) => {
            console.error(error);
            self.setState({
            kategoriOptions: [],
            });
        });        
    }

    sendPostRequest(){
        let self = this;
        if(self.state.gruppnamn.length > 0 && self.state.beskrivning.length > 0 && self.state.granskare !== undefined){

            let behorighet : RestInterface.Behorighet = {
                id: null,
                namn: self.state.gruppnamn,
                kategorier: self.state.kategorier,
                beskrivning: self.state.beskrivning,
                granskare: self.state.granskare,
            }

            RestService.skapaBehorighet(behorighet)
            .then((response) => {
                if (response.ok) {
                return response.json();
                } else {
                throw new Error('Something went wrong');
                }
            })
            .then((results) => {
                window.location.reload();
            })
            .catch((error) => {
                console.error(error);
            });
        }   
    }

    gruppnamnChange(event: any, data: any) {
        this.setState ({
            gruppnamn: data.value,
        });
    }

    kategoriChange(event: any, data: any) {
        this.setState ({
            kategorier: data.value,
        });
    }

    beskrivningChange(event: any, data: any) {
        this.setState ({
            beskrivning: data.value,
        });
    }

    render() {
        return (
            <Form>
                <Form.Input fluid label='Gruppnamn' placeholder='Gruppnamn' onChange={this.gruppnamnChange.bind(this)}/>
                <Form.Group widths='equal'>
                    <Form.Select fluid multiple label='Kategori' options={this.state.kategoriOptions} onChange={this.kategoriChange.bind(this)} placeholder='Kategori' />
                </Form.Group>
                <Form.TextArea label='Beskrivning' placeholder='Ange en beskrivning...' onChange={this.beskrivningChange.bind(this)}/>
                <Form.Input fluid readOnly={true} label='Granskare' placeholder='Per Persson (66015024)' />
                <Form.Button positive onClick={this.sendPostRequest.bind(this)}>Skapa</Form.Button>
            </Form>
        )
    }
}

export default AdminSkapaBehorighet;