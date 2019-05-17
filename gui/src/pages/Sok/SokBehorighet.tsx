import * as React from "react";
import { Table, List, Label, Container, Header, Input, Button } from "semantic-ui-react"; 
import RestInterface from "../../rest/rest-interface";
import RestService from "../../rest/rest-service";

interface Props {
}

interface State {
  isLoading: boolean,
  results: Array<RestInterface.Behorighet>,
  value: string,
    user: RestInterface.User,
  sendPostRequest: boolean,
}

export class SokBehorighet extends React.Component<Props, State> {

  constructor(props: any){
    super(props);
    this.state = {
      isLoading: false,
      results: [],
        user: {
            id: "66120403",
            namn: "Martin Gunnarsson"
        },
      value: "",
      sendPostRequest: false,
    };    
  }

  sendPostRequest(){
    if(this.state.sendPostRequest){
      let self = this;
      RestService.sokBehorighet(this.state.value)
      .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Something went wrong');
          }
      })
      .then((results) => {
        let sok = [];
        for(let index in results) {
          sok.push(results[index]);
        }

        self.setState({
          results: sok,
          sendPostRequest: false,
        });
      })
      .catch((error) => {
          console.error(error);
          self.setState({
            sendPostRequest: false,
          });
      });
    }
  }

  handleChange(event: any, data: any){
    this.setState({
      value: data.value
    });
  }

  handleClick(event: any, data: any){
    this.setState({
      sendPostRequest: true
    }, () => this.sendPostRequest());
  }

    handleBestall(event: any, data: any) {
      console.log(event, data);

        let behorighet: RestInterface.Behorighet = data.behorighet;
        console.log(behorighet)
        let self = this;

        let ansokan: RestInterface.Ansokan = {
            behorighet: behorighet,
            granskare: behorighet.granskare,
            id: null,
            userId: this.state.user.id,
            status: null
        };

        RestService.skapaAnsokan(ansokan.userId, ansokan.behorighet.id)
            .then((response) => {
                if (response.ok) {
                    location.reload();
                } else {
                    throw new Error('Something went wrong');
                }
            })
            .catch((error) => {
                console.error(error);
                self.setState({
                    sendPostRequest: false,
                });
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
        <Table.Cell key={item.id + "beställ"}><Button behorighet={item} onClick={this.handleBestall.bind(this)} positive content={"Beställ"}/></Table.Cell>
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
        <Button positive fluid content={"Sök"} onClick={this.handleClick.bind(this)} disabled={this.state.value.length === 0}/>
        <br/>
        {this.state.results.length > 0 ? this.getResults() : null}

      </Container>
    )
  }
}

export default SokBehorighet;