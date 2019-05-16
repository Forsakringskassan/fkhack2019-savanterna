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
                <div>
                    <Header>Sök Behörighet</Header>
                </div>
                <Grid>
        <Grid.Column width={6}>
          <Search
            fluid
            loading={this.state.isLoading}
            onResultSelect={() => alert("LOL")}
            onSearchChange={() => alert("Lol2")}
            results={[]}
          />
        </Grid.Column>
      </Grid>
            </Container>
        )
    }
}

export default SokBehorighet;