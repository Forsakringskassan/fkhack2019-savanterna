namespace RestInterface{

    export interface dropdownItem {
        key: any;
        text: any;
        value: any;
    }

    export interface Behorighet {
        id: string;
        namn: string;
        kategorier: Array<Kategori>;
        beskrivning: string;
        granskare: User;
    }
    
    export interface Kategori {
        id: string;
        namn: string;
    }
    
    export interface User {
        id: string;
        namn: string;
    }
    
    export interface Ansokan {
        id: string;
        behorighetsId: string;
        userId: string;
        granskarId: string;
    }
}

export default RestInterface;