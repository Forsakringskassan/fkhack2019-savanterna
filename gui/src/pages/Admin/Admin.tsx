import * as React from "react";

import { Header, Container, Tab } from "semantic-ui-react";
import AdminSkapaBehorighet from "./Admin-skapa-behorighet";
import AdminRedigeraBehorighet from "./Admin-redigera-behorighet";

export class Admin extends React.Component<{}, {}> {

    constructor(props: any){
        super(props)
    }

    getPanes() {
        return [
            { menuItem: 'Skapa behörighetsgrupp', render: () => 
                <Tab.Pane>
                    <AdminSkapaBehorighet/>
                </Tab.Pane> },
            { menuItem: 'Redigera behörighetsgrupp', render: () => 
                <Tab.Pane>
                    <AdminRedigeraBehorighet/>
                </Tab.Pane> 
            }
        ]
    }

    render() {
        return (
            <Container>
                <Header as={"h1"} dividing>Administrera behörigheter</Header>
                <Tab panes={this.getPanes()}/>
            </Container>
        )
    }
}

export default Admin;