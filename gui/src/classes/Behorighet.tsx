
class Behorighet {
    id: string;
    name: string;
    description: string;
    categories: Array<string>;

    constructor(id:string, name:string, description: string, categories: Array<string>) {

        this.name = name;
        this.description = description;
        this.categories = categories;
    }
}

export default Behorighet;