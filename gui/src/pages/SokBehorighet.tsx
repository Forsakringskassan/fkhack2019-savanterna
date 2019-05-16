import * as React from "react";
import { Header, Container, Grid, Segment, Search } from "semantic-ui-react"; 

interface Props {
}

interface State {
  isLoading: false,
  results: [],
   value: '';
}
const initialState = { isLoading: false, results: [] as Array<any>, value: '' }

export class SokBehorighet extends React.Component<State, Props> {

    state = initialState
    

    render() {
        return (
            <Container>
                    <Header as={"h1"}>Sök Behörighet</Header>
          <Search
            fluid
            loading={this.state.isLoading}
            onResultSelect={() => ("")}
            onSearchChange={() => ("")}
            results={[]}
          />

      </Container>
        )
    }
}

export default SokBehorighet;