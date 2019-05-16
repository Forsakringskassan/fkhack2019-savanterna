import * as React from "react";
import { Menu, Icon, Segment } from "semantic-ui-react"
import { Route, NavLink, HashRouter } from "react-router-dom";
import 'semantic-ui-css/semantic.min.css'

import Home from "./pages/Home"
import Test from "./pages/Test"
import SokBehorighet from "./pages/SokBehorighet";
import Ansokningar from "./pages/Ansokningar";

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
                    <Menu.Item as={NavLink} exact to="/sokbehorighet">
                    <Icon name='search'/>
                    Sök Behörighet
                    </Menu.Item>
                </Menu>
                <Segment basic className='content'>
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/test" component={Test}/>
                    <Route exact path="/sokbehorighet" component={SokBehorighet}/>
                    <Route exact path="/ansokningar" component={Ansokningar}/>
                </Segment>
            </div>
        </HashRouter>
        )
    }
}