import * as React from "react";

import {List, Popup, Table} from "semantic-ui-react";
import Behorighet from "../classes/Behorighet";

interface Props {
    trigger?: any;
    behorighet: Behorighet;
}

interface State {
    trigger?: any;
    behorighet: Behorighet;
}

export class BehorighetPopup extends React.Component<Props, State> {

    constructor(props: any){
        super(props);

        this.state = {
            trigger: props.trigger,
            behorighet: props.behorighet
        };
    }

    componentWillReceiveProps(nextProps: Readonly<Props>, nextContext: any): void {
        this.setState({
            behorighet: nextProps.behorighet
        })
    }

    render() {
        return (
            <Popup trigger={this.state.trigger}>
                <Popup.Content>
                    <List>
                        <List.Item>
                            <List.Content>
                                <List.Header>
                                    { this.state.behorighet.name}
                                </List.Header>
                                <List.Description>
                                    { this.state.behorighet.description}
                                </List.Description>
                            </List.Content>
                        </List.Item>
                    </List>
                </Popup.Content>
            </Popup>
        )
    }
}

export default BehorighetPopup;