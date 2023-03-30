import axios from 'axios'


const SPRING_BOOT_HOMES_URL = 'http://localhost:8081/homes/'

class HomeService{
    getAllhomes(){
        return axios.get(SPRING_BOOT_HOMES_URL);
    }
    create(home){
        return axios.post('http://localhost:8081/homes/add/address', home)
    }

    // addAddresstonewhome(id, address){
    //     return axios.post('http://localhost:8081/address/id/'+id, address)
    // }
}

export default new HomeService();