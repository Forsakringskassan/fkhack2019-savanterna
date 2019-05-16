import * as React from "react";
import { Header, Container, Grid, Segment, Search, Dropdown } from "semantic-ui-react"; 
import BehorigheterAllUser from "./Behorigheter-all-user";

const mockResults = [{  
  key: "Behörighet1",
  value: "Behörighet1",
  text: "Behörighetsbeskrivning"},
  {key: "Behörighet2",
  value: "Behörighet2",
  text: "Behörighetsbeskrivning2 :D"}]

interface Props {
}

interface State {
  isLoading: boolean,
  results: Array<any>,
   value: any,
  
}
const initialState = { isLoading: false, results: [] as Array<any>, value: '', }

export class SokBehorighet extends React.Component<State, Props> {

    state = {isLoading: false,
    results: [] as Array<any>,
    value: 'Sök...'}


    render() {
      
        return (
            <Container>
                    <Header as={"h1"}>Sök Behörighet</Header>
          <Dropdown
            fluid
            search
            selection
            options={mockResults}
          

          
          
          
          />

      </Container>
        )
    }
}

export default SokBehorighet;