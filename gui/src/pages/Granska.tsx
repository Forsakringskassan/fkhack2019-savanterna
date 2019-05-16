import * as React from "react";

import { Segment, Header, Card, Table, Button, Container } from "semantic-ui-react";
import Behorighet from "../classes/Behorighet";

export interface ansokning {
    user: string,
    permission: Behorighet,
}

export class Granska extends React.Component<{}, {}> {

    inkomnaAnsokningar : Array<ansokning> = [
        {
            user: "88880001",
            permission: {
                id: "1",
                name: "Gwin10_SuperUser",
                description: "Ger användaren SuperUser på Windows 10",
                categories: []
            },
        },
        {
            user: "88880002",
            permission: {
                id: "2",
                name: "GStashUser",
                description: "Ger användaren behörighet att läsa och redigera i Stash",
                categories: []
            },
        }
    ]

    tilldeladeAnsokningar : Array<ansokning> = [
        {
            user: "88880001",
            permission: {
                id: "3",
                name: "GConfluence_User",
                description: "Ger användaren behörighet att läsa och redigera i Confluence",
                categories: []
            },
        },
        {
            user: "88880001",
            permission: {
                id: "3",
                name: "GSkype_Admin",
                description: "Ger användaren behörighet att använda Skype",
                categories: []
            },
        }
    ]

    constructor(props: any){
        super(props)
    }

    getRowsInkomna(){
        let rows = [];

        for(let i in this.inkomnaAnsokningar){
            let ansokning : ansokning = this.inkomnaAnsokningar[i];
            rows.push(             
                <Table.Row key={i + "row"}>
                    <Table.Cell content={ansokning.user} key={i + ansokning.user}/>
                    <Table.Cell content={ansokning.permission.name} key={i + ansokning.permission.name}/>
                    <Table.Cell key={i + "buttons"}>
                        <Button.Group>
                            <Button content={"Neka"} negative/>
                            <Button.Or />
                            <Button content={"Godkänn"} positive/>
                        </Button.Group>
                    </Table.Cell>                    
                </Table.Row>
            )
        }

        return rows;
    }

    getRowsTilldelade(){
        let rows = [];

        for(let i in this.tilldeladeAnsokningar){
            let ansokning : ansokning = this.tilldeladeAnsokningar[i];
            rows.push(             
                <Table.Row key={i + "row"}>                    
                    <Table.Cell content={ansokning.user} key={i + ansokning.user}/>
                    <Table.Cell content={ansokning.permission.name} key={i + ansokning.permission.name}/>
                    <Table.Cell content={<Button content={"Ta bort"} negative/>} key={i + "buttons"}/>               
                </Table.Row>
            )
        }

        return rows;
    }

    render() {
        return (
            <Container>
                <Header as={"h1"}>Granska</Header>

                <Header as={"h2"} dividing>Inkomna ansökningar</Header>
                <Table padded compact>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell content={"Användare"}/>
                            <Table.HeaderCell content={"Behörighet"}/>
                            <Table.HeaderCell content={"Godkänn/Neka"}/>
                        </Table.Row>
                    </Table.Header>
                        <Table.Body>
                            {this.getRowsInkomna()}
                        </Table.Body>
                    </Table>


                <Header as={"h2"} dividing>Tilldelade behörigheter</Header>
                <Table padded compact>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell content={"Användare"}/>
                            <Table.HeaderCell content={"Behörighet"}/>
                            <Table.HeaderCell content={"Redigera"}/>
                        </Table.Row>
                    </Table.Header>
                        <Table.Body>
                            {this.getRowsTilldelade()}
                        </Table.Body>
                    </Table>
            </Container>
        )
    }
}

export default Granska;