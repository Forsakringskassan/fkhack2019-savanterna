import * as React from "react";

import { Header, Form } from "semantic-ui-react";

export class AdminSkapaBehorighet extends React.Component<{}, {}> {

    constructor(props: any){
        super(props)
    }

    miljoOptions = [
        { key: 'neptunus', text: 'Neptunus', value: 'neptunus' },
        { key: 'mars', text: 'Mars', value: 'mars' },
        { key: 'verif', text: 'Verif', value: 'verif' },        
    ]

    yrkesrollerOptions = [
        { key: 'systemutvecklare', text: 'Systemutvecklare', value: 'systemutvecklare' },
        { key: 'itsamordnare', text: 'IT-Samordnare', value: 'itsamordnare' },
        { key: 'testare', text: 'Testare', value: 'testare' },
    ]

    render() {
        return (
            <Form>
                <Form.Input fluid label='Gruppnamn' placeholder='Gruppnamn' />
                <Form.Group widths='equal'>
                    <Form.Select fluid multiple label='Miljö' options={this.miljoOptions} placeholder='Miljö' />
                    <Form.Select fluid multiple label='Yrkesroll' options={this.yrkesrollerOptions} placeholder='Yrkesroll' />
                </Form.Group>
                <Form.TextArea label='Beskrivning' placeholder='Ange en beskrivning...' />
                <Form.Input fluid readOnly={true} label='Granskare' placeholder='Per Persson (66015024)' />
                <Form.Button positive>Skapa</Form.Button>
            </Form>
        )
    }
}

export default AdminSkapaBehorighet;