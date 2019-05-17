import * as React from "react";

import {Header, Container, Tab} from "semantic-ui-react";
import Behorighet from "../../classes/Behorighet";
import BehorigheterAllUser from "./Behorigheter-all-user";
import BehorigheterOngoing from "./Behorigheter-ongoing";
import RestService from "../../rest/rest-service";
import RestInterface from "../../rest/rest-interface";

interface State {
    behorigheter: Array<Behorighet>;
    ongoing: Array<RestInterface.Ansokan>;
    sendPostRequest: boolean;
    user: RestInterface.User;
}

export class Behorigheter extends React.Component<{}, State> {

    constructor(props: any){
        super(props);

        this.state = {
            behorigheter: [],
            ongoing: [],
            sendPostRequest: true,
            user: {
                id: "66120403",
                namn: "Martin Gunnarsson"
            }
        };
    }

    componentDidMount(): void {
        this.getBehorigheter();
        this.getAnsokningar();
    }

    getAnsokningar(){
        if(this.state.sendPostRequest){
            let self = this;
            RestService.hamtaUserAnsokningar(this.state.user.id)
                .then((response) => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Something went wrong');
                    }
                })
                .then((results) => {
                    let sok = [];
                    for(let index in results) {
                        sok.push(results[index]);
                    }

                    console.log(sok)

                    self.setState({
                        sendPostRequest: false,
                        ongoing: sok
                    });
                })
                .catch((error) => {
                    console.error(error);
                    self.setState({
                        sendPostRequest: false,
                    });
                });
        }
    }

    getBehorigheter(){
        if(this.state.sendPostRequest){
            let self = this;
            RestService.hamtaUserBehorigheter(this.state.user.id)
                .then((response) => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Something went wrong');
                    }
                })
                .then((results) => {
                    let sok = [];
                    for(let index in results) {
                        sok.push(results[index]);
                    }

                    self.setState({
                        sendPostRequest: false,
                        behorigheter: sok
                    });
                })
                .catch((error) => {
                    console.error(error);
                    self.setState({
                        sendPostRequest: false,
                    });
                });
        }
    }

    getPanes() {
        return [
            { menuItem: 'Pågående ansökningar', render: () => <Tab.Pane>
                    <BehorigheterOngoing ongoing={this.state.ongoing}/>
                </Tab.Pane> },
            { menuItem: 'Mina behörigheter', render: () => <Tab.Pane>
                    <BehorigheterAllUser behorigheter={this.state.behorigheter}/>
                </Tab.Pane> }
        ]
    }

    render() {

        return (
            <Container>
                <Header as={"h1"} dividing>Behörigheter och ansökningar</Header>

                <Tab panes={this.getPanes()}/>
            </Container>
        )
    }
}

export default Behorigheter;