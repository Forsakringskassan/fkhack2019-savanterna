import * as React from "react";

import { Segment, Header, Card, Table, Button, Container } from "semantic-ui-react";

export interface ansokning {
    user: string,
    permission: string,
}

export class Granska extends React.Component<{}, {}> {

    inkomnaAnsokningar : Array<ansokning> = [
        {
            user: "88880001",
            permission: "Gwin10_SuperUser",
        },
        {
            user: "88880002",
            permission: "GStashUser",
        }
    ]

    tilldeladeAnsokningar : Array<ansokning> = [
        {
            user: "88880001",
            permission: "GConfluence_User",
        },
        {
            user: "88880001",
            permission: "GSkype_Admin",
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
                    <Table.Cell content={ansokning.permission} key={i + ansokning.permission}/>
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
                    <Table.Cell content={ansokning.permission} key={i + ansokning.permission}/>
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
                <Table padded>
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
                <Table padded>
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