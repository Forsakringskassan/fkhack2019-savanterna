import * as React from "react";
import { Menu, Icon, Segment } from "semantic-ui-react"
import { Route, NavLink, HashRouter } from "react-router-dom";
import 'semantic-ui-css/semantic.min.css'

import Home from "./pages/Home"
import SokBehorighet from "./pages/Sok/SokBehorighet";
import Behorigheter from "./pages/Behorigheter/Behorigheter";
import Granska from "./pages/Granska/Granska"
import Admin from "./pages/Admin/Admin"

export class App extends React.Component<{}, {}> {

    constructor(props: any){
        super(props)
    }

    render() {
        return (
            <HashRouter>
                <div>
                    <Menu attached inverted color='green'>
                        <Menu.Item as={NavLink} exact to="/">
                            <Icon name='home' />
                            Home
                        </Menu.Item>

                        <Menu.Item position='right'>
                            <Icon name='user'/>
                            Inloggad som: Martin Gunnarsson (66120403)
                        </Menu.Item>
                    </Menu>
                    <Segment basic className='content'>
                        <Route exact path="/" component={Home}/>
                        <Route exact path="/sokbehorighet" component={SokBehorighet}/>
                        <Route exact path="/behorigheter" component={Behorigheter}/>
                        <Route exact path="/ansokningar" component={Behorigheter}/>
                        <Route exact path="/granska" component={Granska}/>
                        <Route exact path="/admin" component={Admin}/>
                    </Segment>            
                </div>
            </HashRouter>
        )
    }
}