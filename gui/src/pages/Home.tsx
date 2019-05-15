import * as React from "react";

import { Segment, Header } from "semantic-ui-react";

export class Home extends React.Component<{}, {}> {

    constructor(props: any){
        super(props)
    }

    render() {
        return (
        <div>
                <Header>Behörighetsportalen</Header>
        </div>
        )
    }
}

export default Home;