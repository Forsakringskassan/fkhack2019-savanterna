import * as React from "react";
import { Menu, Icon, Segment, Container, Header, Button } from "semantic-ui-react"
import { Route, NavLink, HashRouter } from "react-router-dom";
import 'semantic-ui-css/semantic.min.css'

import Home from "./pages/Home"
import SokBehorighet from "./pages/SokBehorighet";
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
                <Menu attached inverted color='olive'>
                    <Menu.Item as={NavLink} exact to="/">
                            <Icon name='home' />
                            Home
                        </Menu.Item>

                        <Menu.Item position='right'>
                        <Icon name='user'/>
                        Inloggad som: Per Persson (66015024)
                    </Menu.Item>
                    </Menu>
                <Segment basic className='content'>
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/sokbehorighet" component={SokBehorighet}/>
                    <Route exact path="/behorigheter" component={Behorigheter}/>
                    <Route exact path="/ansokningar" component={Behorigheter}/>
                    <Route exact path="/granska" component={Granska}/>
                    </Segment>
            
                </div>
        </HashRouter>
        )
    }
}