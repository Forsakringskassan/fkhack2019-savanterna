import * as React from "react";
import { Card, Table, List, Label, Container, Header, Input, Button } from "semantic-ui-react"; 
import RestInterface from "../../rest/rest-interface";

interface Props {
}

interface State {
  isLoading: boolean,
  results: Array<RestInterface.Behorighet>,
  value: any,  
}

export class SokBehorighet extends React.Component<Props, State> {

  b1: RestInterface.Behorighet = {
    id: "1", 
    namn: "GStashUser", 
    beskrivning: "Ger läsrättigheter till Stash", 
    kategorier: [{id: "1", namn: "Systemutvecklare"}],
    granskare: {id: "1", namn: "Kalle Karlsson"}  
  };
  b2: RestInterface.Behorighet = {
    id: "2", 
    namn: "GConfluenceUser", 
    beskrivning: "Ger läsrättigheter till Confluence", 
    kategorier: [{id: "1", namn: "Systemutvecklare"}, {id: "2", namn: "IT-Samordnare"}],
    granskare: {id: "2", namn: "Anna Andersson"}  
  };
  b3: RestInterface.Behorighet = {
    id: "3", 
    namn: "GSuperUserWin10", 
    beskrivning: "Ger sudo-rättigheter till Windows 10", 
    kategorier: [{id: "1", namn: "Systemutvecklare"}],
    granskare: {id: "2", namn: "Anna Andersson"}  
  };

  constructor(props: any){
    super(props);
    this.state = {
      isLoading: false,
      results: [this.b1, this.b2, this.b3],
      value: ""
    };    
  }

  handleChange(event: any, data: any){
    this.setState({
      value: data.value
    });
  }

  getResults(){
    let results = this.state.results;
    let search = this.state.value.toLowerCase();

    let filteredData : Array<RestInterface.Behorighet> = results.filter(behorighet => 
      behorighet.namn.toLowerCase().includes(search) ||
      behorighet.beskrivning.toLowerCase().includes(search) ||
      behorighet.kategorier.some(kategori => kategori.namn.toLowerCase().includes(search))
    );

    let rows = filteredData.map(item => (
      <Table.Row key={item.id + "row"}>
        <Table.Cell content={item.namn} key={item.id + item.namn}/>
        <Table.Cell content={item.beskrivning} key={item.id + item.beskrivning}/>
        <Table.Cell>
          <List key={item.id + "kategorier"}>
            {item.kategorier.map((kategori) =>
                <List.Item key={item.id + kategori.id}>
                    <Label content={kategori.namn}/>
                </List.Item>
            )}
          </List>
        </Table.Cell>
        <Table.Cell key={item.id + "beställ"}><Button positive content={"Beställ"}/></Table.Cell>
      </Table.Row>
    ));
    
    return(
      <div>
        <Header as={"h2"} dividing>Resultat:</Header>        
        <Table padded key={"results table"}>          
          <Table.Header>
            <Table.Row key={"header row"}>
              <Table.HeaderCell content={"Gruppnamn"} key={"gruppnamn header"}/>
              <Table.HeaderCell content={"Beskrivning"} key={"beskrivning header"}/>
              <Table.HeaderCell content={"Kategorier"} key={"kategorier header"}/>
              <Table.HeaderCell content={"Beställ"} key={"beställ header"}/>
            </Table.Row>
          </Table.Header>          
          <Table.Body>
            {rows}            
          </Table.Body>          
        </Table>
      </div>
    )
      
  }

  render() {    
    return (
      <Container>
        <Header as={"h1"} dividing>Sök Behörighet</Header>
        <Input fluid icon={'search'} placeholder={'Sök...'} onChange={this.handleChange.bind(this)}/>
        <br/>
        {this.state.value.length > 0 ? this.getResults() : null}

      </Container>
    )
  }
}

export default SokBehorighet;