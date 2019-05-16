import * as React from "react";
import { Header, Container, Grid, Segment, Search, Dropdown } from "semantic-ui-react"; 
import BehorigheterAllUser from "./Behorigheter-all-user";

const mockResults = [{  
  key: "GIT User",
  value: "Git user",
  text: "Git user - Behörighet för att komma åt GIT"
  },
  {
  key: "Stash user",
  value: "Stash user",
  text: "Stash user - Behörighet för att komma åt Stash."
},
{
  key: "BLIP user",
  value: "BLIP user",
  text: "BLIP user - Behörighet för att använda BLIP",
},
{
  key: "BLIP Admin",
  value: "BLIP Admin",
  text: "BLIP Admin -Behörighet för att administrera BLIP",
},{
  key: "Citrix user",
  value: "Citrix user",
  text: "Citrix user - Behörighet för åtkomst till Citrix",
}]

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
            scrolling

          />

      </Container>
        )
    }
}

export default SokBehorighet;