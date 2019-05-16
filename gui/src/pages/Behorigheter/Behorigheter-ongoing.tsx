import * as React from "react";

import {Table} from "semantic-ui-react";
import Behorighet from "../../classes/Behorighet";

interface Props {
    ongoing: Map<Behorighet, string>;
}

interface State {
    ongoing: Map<Behorighet, string>;
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
        this.state.ongoing.forEach((status, behorighet, other) => {
            render.push(
                <Table.Row key={behorighet.id} error={status === "Ej godkänd"} positive={status === "Godkänd"} >
                    <Table.Cell>
                        {behorighet.name}
                    </Table.Cell>
                    <Table.Cell>
                        {status}
                    </Table.Cell>
                </Table.Row>
            );
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