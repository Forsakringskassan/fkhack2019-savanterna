import * as React from "react";

import {List} from "semantic-ui-react";
import Behorighet from "../classes/Behorighet";

interface Props {
    ansokningar: Array<Behorighet>;
}

interface State {
    ansokningar: Array<Behorighet>;
}

export class BehorigheterAllUser extends React.Component<Props, State> {

    constructor(props: any){
        super(props);

        this.state = {
            ansokningar: props.ansokningar
        };
    }

    componentWillReceiveProps(nextProps: Readonly<Props>, nextContext: any): void {
        this.setState({
            ansokningar: nextProps.ansokningar
        })
    }

    getList() {
        let render: Array<any> = [];
        this.state.ansokningar.forEach(item => {
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