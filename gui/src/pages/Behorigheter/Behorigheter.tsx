import * as React from "react";

import {Header, Container, Tab} from "semantic-ui-react";
import Behorighet from "../../classes/Behorighet";
import BehorigheterAllUser from "./Behorigheter-all-user";
import BehorigheterOngoing from "./Behorigheter-ongoing";

interface State {
    behorigheter: Array<Behorighet>;
    ongoing: Map<Behorighet, string>;
}

export class Behorigheter extends React.Component<{}, State> {

    constructor(props: any){
        super(props);

        let b1:Behorighet = new Behorighet("1", "GStashUser", "Ger läsrättigheter till Stash", ["Systemutvecklare"]);
        let b2:Behorighet = new Behorighet("2", "GConfluenceUser", "Ger läsrättigheter till Confluence", ["Systemutvecklare", "IT-Samordnare", "Confluence"]);
        let b3:Behorighet = new Behorighet("3", "GSuperUserWin10", "Ger sudo-rättigheter till Windows 10", ["Systemutvecklare"]);
        let b4:Behorighet = new Behorighet("4", "GSkypeUser", "Ger rättigheter att ringa via Skype", ["Systemutvecklare", "Testare", "IT-samordnare"]);

        let ongoing = new Map<Behorighet, string>();
        ongoing.set(b1, "Pågående");
        ongoing.set(b2, "Godkänd");
        ongoing.set(b3, "Ej godkänd");
        ongoing.set(b4, "Godkänd");

        let behorigheter: Array<Behorighet> = [];
        behorigheter.push(b1);
        behorigheter.push(b2);
        behorigheter.push(b3);
        behorigheter.push(b4);

        this.state = {
            behorigheter: behorigheter,
            ongoing: ongoing
        };
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