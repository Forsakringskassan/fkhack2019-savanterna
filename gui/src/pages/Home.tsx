import * as React from "react";
import { Grid } from 'semantic-ui-react'
import { Segment, Header } from "semantic-ui-react";
import { Button } from "semantic-ui-react";
import { Container} from 'semantic-ui-react';

export class Home extends React.Component<{}, {}> {
    
    constructor(props: any){
        super(props)
    }

    handleButton(data: any){
        window.location.href = window.location.href + data;
    }

    render() {
        return (
        <Container>
                <Header as='h1'>Behörighetsportalen</Header>
                <br></br>
                <br></br>
                <Header as='h2'>
                <Button fluid onClick={()=>this.handleButton("SokBehorighet")} color='olive' size='massive'>Sök behörigheter</Button>
                <Header.Subheader>Här kan du söka behörigheter, behSörighetsgrupper eller miljögrupper</Header.Subheader>
                </Header>

                <Header as='h2'>
                <Button fluid onClick={()=>this.handleButton("Behorigheter")} color='olive' size='massive'>Mina behörigheter</Button>
                <Header.Subheader>Här kan du se pågående och avslutade behörighetsprocesser samt tilldelade behörigheter</Header.Subheader>
                </Header>

                <Header as='h2'>
                <Button fluid onClick={()=>this.handleButton("granska")} color='olive' size='massive'>Granska behörigheter</Button>
                <Header.Subheader>Här kan du granska behörigheter som dina undersåtar skickat in</Header.Subheader>
                </Header>

                <Header as='h2'>
                <Button fluid onClick={()=>this.handleButton("admin")} color='olive' size='massive'>Administrera behörigheter</Button>
                <Header.Subheader>Här kan du administrera behörighetsgrupper</Header.Subheader>
                </Header>

                <Header as='h2'>
                <Button fluid onClick={()=>this.handleButton("ovrigt")} color='olive' size='massive'>Om oss, support, kontakta oss osvsov</Button>
                <Header.Subheader>Här kan du göra allt som man egentligen inte alls använder</Header.Subheader>
                </Header>

        </Container>

        )
    }
}

export default Home;