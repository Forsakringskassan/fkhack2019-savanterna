import * as React from "react";

import {Table} from "semantic-ui-react";
import RestInterface from "../../rest/rest-interface";

interface Props {
    ongoing: Array<RestInterface.Ansokan>;
}

interface State {
    ongoing: Array<RestInterface.Ansokan>;
}

export class BehorigheterOngoing extends React.Component<Props, State> {

    constructor(props: any){
        super(props);

        this.state = {
            ongoing: props.ongoing
        };
    }

    componentWillReceiveProps(nextProps: Readonly<Props>, nextContext: any): void {
        this.setState({
            ongoing: nextProps.ongoing
        })
    }

    getRows() {
        let render: Array<any> = [];
        this.state.ongoing.forEach((item) => {
            console.log(item)
            /*render.push(
                <Table.Row key={item.} error={status === "Ej godkänd"} positive={status === "Godkänd"} >
                    <Table.Cell>
                        {behorighet.name}
                    </Table.Cell>
                    <Table.Cell>
                        {status}
                    </Table.Cell>
                </Table.Row>
            );*/
        });

        return render;
    }

    render() {
        return (
            <Table>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>
                            Behörighet
                        </Table.HeaderCell>
                        <Table.HeaderCell>
                            Status
                        </Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {this.getRows()}
                </Table.Body>
            </Table>
        )
    }
}

export default BehorigheterOngoing;