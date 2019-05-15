import * as React from "react";

import { Segment, Header, Container } from "semantic-ui-react";

export class Test extends React.Component<{}, {}> {

    constructor(props: any){
        super(props)
    }

    render() {
        return (
            <Container>
                <Header>TEST</Header>
            </Container>
        )
    }
}

export default Test;