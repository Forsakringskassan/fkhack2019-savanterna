
class Behorighet {
    id: string;
    name: string;
    description: string;
    categories: Array<string>;

    constructor(id:string, name:string, description: string, categories: Array<string>) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
    }
}

export default Behorighet;