import * as React from "react";

import {List} from "semantic-ui-react";
import Behorighet from "../../classes/Behorighet";

interface Props {
    behorigheter: Array<Behorighet>;
}

interface State {
    behorigheter: Array<Behorighet>;
}

export class BehorigheterAllUser extends React.Component<Props, State> {

    constructor(props: any){
        super(props);

        this.state = {
            behorigheter: props.behorigheter
        };
    }

    componentWillReceiveProps(nextProps: Readonly<Props>, nextContext: any): void {
        this.setState({
            behorigheter: nextProps.behorigheter
        })
    }

    getList() {
        let render: Array<any> = [];
        if(this.state.behorigheter !== undefined) {
            this.state.behorigheter.forEach(item => {
                render.push(<List.Item key={item.id}>
                    <List.Content>
                        <List.Header>
                            { item.name }
                        </List.Header>
                        <List.Description>
                            { item.description }
                        </List.Description>
                    </List.Content>
                </List.Item>);
            });
        }

        return render;
    }

    render() {
        return (
            <List divided>
                { this.getList() }
            </List>
        )
    }
}

export default BehorigheterAllUser;