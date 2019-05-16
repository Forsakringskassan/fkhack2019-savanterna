import * as React from "react";
import { Menu, Icon, Segment, Container, Header, Button } from "semantic-ui-react"
import { Route, NavLink, HashRouter } from "react-router-dom";
import 'semantic-ui-css/semantic.min.css'

import Home from "./pages/Home"
import Behorigheter from "./pages/Behorigheter";
import Granska from "./pages/Granska"

export class App extends React.Component<{}, {}> {
    forceUpdate(callBack?: () => void): void {
    }

    constructor(props: any){
        super(props)
    }

    render() {

        return (
        <HashRouter>
            <div>
                <Menu attached inverted color='teal'>
                    <Menu.Item as={NavLink} exact to="/">
                        <Icon name='home' />
                        Home
                    </Menu.Item>
                    <Menu.Item as={NavLink} exact to="/ansokningar">
                        <Icon name='circle' />
                        Ansökningar
                    </Menu.Item>
                    <Menu.Item as={NavLink} exact to="/granska">
                        <Icon name='circle' />
                        Granska
                    </Menu.Item>

                </Menu>
                <Segment basic className='content'>
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/ansokningar" component={Behorigheter}/>
                    <Route exact path="/granska" component={Granska}/>
                </Segment>

        </div>
        </HashRouter>
        )
    }
}