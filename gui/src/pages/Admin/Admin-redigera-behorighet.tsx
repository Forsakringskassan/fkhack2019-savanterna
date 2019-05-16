import * as React from "react";

import { Table, Button, Label, List } from "semantic-ui-react";
import Behorighet from "../../classes/Behorighet";

export class AdminRedigeraBehorighet extends React.Component<{}, {}> {

    constructor(props: any){
        super(props)
    }

    b1:Behorighet = new Behorighet("1", "Behörighet1", "Denna används för blip", ["Systemutvecklare", "IT-samordnare"]);
    b2:Behorighet = new Behorighet("2", "Behörighet2", "Denna används för asdasd", ["Mars", "Sysadmin"]);
    b3:Behorighet = new Behorighet("3", "Behörighet3", "Denna används för dededede", ["Jupiter"]);
    b4:Behorighet = new Behorighet("4", "Behörighet4", "Denna används för deasdaersf", []);

    behorigheter : Array<Behorighet> = [this.b1,this.b2, this.b3, this.b4]

    getRowsInkomna(){
        let rows = [];
        for(let i in this.behorigheter){
            let behorighet : Behorighet = this.behorigheter[i];
            rows.push(             
                <Table.Row key={i + "row"}>
                    <Table.Cell content={behorighet.id} key={i + behorighet.id}/>
                    <Table.Cell content={behorighet.name} key={i + behorighet.name}/>
                    <Table.Cell content={behorighet.description} key={i + behorighet.description}/>
                    <Table.Cell key={i + "kategorier"}>
                        <List key={i + "list"}>
                            {behorighet.categories.map((kategori) =>
                                <List.Item key={i + kategori}>
                                    <Label content={kategori}/>
                                </List.Item>
                            )}
                        </List>
                    </Table.Cell>
                    <Table.Cell key={i + "buttons"}>
                        <Button.Group>
                            <Button content={"Radera"} negative/>
                            <Button.Or />
                            <Button content={"Redigera"} primary/>
                        </Button.Group>
                    </Table.Cell>
                </Table.Row>
            )
        }
        return rows;
    }

    render() {
        return (
            <Table padded compact>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell content={"Id"}/>
                        <Table.HeaderCell content={"Behörighetsgrupp"}/>
                        <Table.HeaderCell content={"Beskrivning"}/>
                        <Table.HeaderCell content={"Kategorier"}/>
                        <Table.HeaderCell content={"Redigera"}/>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {this.getRowsInkomna()}
                </Table.Body>
            </Table>
        )
    }
}

export default AdminRedigeraBehorighet;