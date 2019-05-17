import RestInterface from "./rest-interface";

const API = "http://localhost:8080/rest";

const RestService = {

    getApi(){ return(this.API); },
    getBehorighetEndpoint(){ return(API + "/v1/behorighet"); },
    getKategoriEndpoint(){ return(API + "/v1/kategori"); },
    getUserEndpoint(){ return(API + "/v1/user"); },
    getAnsokningarEndpoint(){ return(API + "/v1/ansokan"); },

    skapaBehorighet(behorighet: RestInterface.Behorighet){
        return(fetch(this.getBehorighetEndpoint() + "/skapa", { method: 'POST', body: JSON.stringify(behorighet) }));
    },

    uppdateraBehorighet(behorighet: RestInterface.Behorighet){
        return(fetch(this.getBehorighetEndpoint() + "/uppdatera", { method: 'POST', body: JSON.stringify(behorighet) }));
    },

    raderaBehorighet(id: string){
        return(fetch(this.getBehorighetEndpoint() + "/radera/" + id, { method: 'POST' }));
    },

    hamtaBehorighet(){
        return(fetch(this.getBehorighetEndpoint() + "/hamta", { method: 'GET' }));
    },

    sokBehorighet(input: string){
        return(fetch(this.getBehorighetEndpoint() + "/sok?input=" + input, { method: 'GET' }));
    },

    skapaKategori(kategori: RestInterface.Kategori){
        return(fetch(this.getKategoriEndpoint() + "/skapa", { method: 'POST', body: JSON.stringify(kategori) }));
    },

    hamtaKategorier(){
        return(fetch(this.getKategoriEndpoint() + "/hamta", { method: 'GET' }));
    },

    skapaUser(user: RestInterface.User){
        return(fetch(this.getKategoriEndpoint() + "/skapa", { method: 'POST', body: JSON.stringify(user) }));
    },

    hamtaUserBehorigheter(userId: string){
        return(fetch(this.getKategoriEndpoint() + "behorigheter/hamta/" + userId, { method: 'GET' }));
    },

    hamtaUserAnsokningar(userId: string){
        return(fetch(this.getKategoriEndpoint() + "ansokningar/hamta/" + userId, { method: 'GET' }));
    },

    skapaAnsokan(ansokan: RestInterface.Ansokan){
        return(fetch(this.getAnsokningarEndpoint() + "/skapa", { method: 'POST', body: JSON.stringify(ansokan) }));
    },

    uppdateraAnsokan(ansokanId: string){
        return(fetch(this.getAnsokningarEndpoint() + "/uppdatera/" + ansokanId, { method: 'PUT' }));
    },

    raderaAnsokan(ansokanId: string){
        return(fetch(this.getAnsokningarEndpoint() + "/radera/" + ansokanId, { method: 'DELETE' }));
    },

}
export default RestService;