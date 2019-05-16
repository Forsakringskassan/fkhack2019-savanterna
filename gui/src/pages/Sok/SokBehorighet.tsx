import * as React from "react";
import { Header, Container, Dropdown } from "semantic-ui-react"; 

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

export class SokBehorighet extends React.Component<State, Props> {

  constructor(props: any){
    super(props);
    this.state = {
      isLoading: false,
      results: [],
      value: ""
    };    
  }

  render() {    
    return (
      <Container>
        <Header as={"h1"} dividing>Sök Behörighet</Header>
        <Dropdown
          fluid
          search
          selection
          options={mockResults}
          scrolling
          placeholder={"Sök..."}
        />

      </Container>
    )
  }
}

export default SokBehorighet;