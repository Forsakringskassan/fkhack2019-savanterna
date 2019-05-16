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

        let b1:Behorighet = new Behorighet("1", "Behörighet1", "Denna används för blip", []);
        let b2:Behorighet = new Behorighet("2", "Behörighet2", "Denna används för asdasd", []);
        let b3:Behorighet = new Behorighet("3", "Behörighet3", "Denna används för dededede", []);
        let b4:Behorighet = new Behorighet("4", "Behörighet4", "Denna används för deasdaersf", []);

        let ongoing = new Map<Behorighet, string>();
        ongoing.set(b1, "Pågående");
        ongoing.set(b2, "Ej godkänd");
        ongoing.set(b3, "Godkänd");

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
                <Header as={"h1"}>Behörigheter och ansökningar</Header>

                <Tab panes={this.getPanes()}/>
            </Container>
        )
    }
}

export default Behorigheter;