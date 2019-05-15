import * as React from "react";
import { Menu, Icon, Segment } from "semantic-ui-react"
import { Route, NavLink, HashRouter } from "react-router-dom";
import 'semantic-ui-css/semantic.min.css'

import Home from "./pages/Home"
import Test from "./pages/Test"

export class App extends React.Component<{}, {}> {

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
                    <Menu.Item as={NavLink} exact to="/test">
                        <Icon name='circle' />
                        Test
                    </Menu.Item>
                </Menu>
                <Segment basic className='content'>
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/test" component={Test}/>
                </Segment>
            </div>
        </HashRouter>
        )
    }
}