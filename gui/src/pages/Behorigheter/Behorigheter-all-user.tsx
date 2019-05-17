import * as React from "react";

import {Label, List} from "semantic-ui-react";
import RestInterface from "../../rest/rest-interface";

interface Props {
    behorigheter: Array<RestInterface.Behorighet>;
}

interface State {
    behorigheter: Array<RestInterface.Behorighet>;
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

                let kategorier:Array<any> = [];
                item.kategorier.forEach(kat => {
                    kategorier.push(<Label key={kat.id} >{ kat.namn }</Label>)
                });

                render.push(<List.Item key={item.id}>
                    <List.Content>
                        <List.Header>
                            { item.namn }
                        </List.Header>
                        <List.Description>
                            { item.beskrivning }
                            <br/>
                            {kategorier}
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