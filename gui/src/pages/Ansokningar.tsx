import * as React from "react";

import {Segment, Header, Container, List} from "semantic-ui-react";
import Behorighet from "../classes/Behorighet";
import BehorigheterAllUser from "./Behorigheter-all-user";

interface State {
    ansokningar: Array<Behorighet>;
}

export class Ansokningar extends React.Component<{}, State> {

    constructor(props: any){
        super(props);

        this.state = {

            ansokningar: [
                {
                    id: "1",
                    name: "Behörighet 1",
                    description: "Denna används för blip",
                    categories: []
                },
                {
                    id: "2",
                    name: "Behörighet 2",
                    description: "Denna används för blaiblaha",
                    categories: []
                },
                {
                    id: "3",
                    name: "Behörighet 3",
                    description: "Denna används för sadasd",
                    categories: []
                },
                {
                    id: "4",
                    name: "Behörighet 4",
                    description: "Denna används för test",
                    categories: []
                }
            ]
        };
    }

    render() {
        return (
            <Container>
                <Header as={"h1"}>Behörigheter och ansökningar</Header>

                <Header as={"h2"}>Mina behörigheter</Header>
                <BehorigheterAllUser ansokningar={this.state.ansokningar}></BehorigheterAllUser>

            </Container>
        )
    }
}

export default Ansokningar;