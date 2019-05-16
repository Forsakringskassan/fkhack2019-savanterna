import * as React from "react";
import { Header } from "semantic-ui-react";
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
                <Header as='h1' dividing>
                    Behörighetsportalen
                </Header>

                <Header as='h2'>
                    <Button fluid onClick={()=>this.handleButton("SokBehorighet")} color='green' size='massive'>
                        Sök behörigheter
                    </Button>
                </Header>

                <Header as='h2'>
                    <Button fluid onClick={()=>this.handleButton("Behorigheter")} color='green' size='massive'>
                        Mina behörigheter
                    </Button>
                </Header>

                <Header as='h2'>
                    <Button fluid onClick={()=>this.handleButton("granska")} color='green' size='massive'>
                        Granska behörigheter
                    </Button>
                </Header>

                <Header as='h2'>
                    <Button fluid onClick={()=>this.handleButton("admin")} color='green' size='massive'>
                        Administrera behörigheter
                    </Button>
                </Header>
        </Container>

        )
    }
}

export default Home;