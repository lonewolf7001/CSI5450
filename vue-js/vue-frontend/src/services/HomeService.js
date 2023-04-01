import axios from 'axios'

const SERVER_URL ='http://csi5450finalprojectbackend-env.eba-vdqznnev.us-east-2.elasticbeanstalk.com/'
class HomeService{
    getAllhomes(){
        return axios.get(SERVER_URL+'/homes/');
    }
    create(home){
        return axios.post(SERVER_URL+'homes/add/address', home)
    }

    getAllcities(){
        return axios.get(SERVER_URL+'city/')
    }

    getHomesbycity(city){
        return axios.get(SERVER_URL+'homes/city?city='+city)
    }
    
    getHomesbycityandprice(price_min,price_max,city){
        return axios.get(SERVER_URL+'homes/price?min='+price_min+'&max='+price_max+'&city='+city)
    }

    getHomesbysoldcount(min,max){
        return axios.get(SERVER_URL+'homes/soldcount?min='+min+'&max='+max)
    }

    getAddressofhome(id){
        return axios.get(SERVER_URL+'address/home/'+id)
    }

    // deleteHomebyId(homeId){
    //     return axios.delete(SERVER_URL+'homes/'+homeId,homeId)
    // }
    update(id, home){
        return axios.put(SERVER_URL+'homes/'+id, home)
    }

    sell(home){
        return axios.post(SERVER_URL+'homes/sell', home)
    }
}

export default new HomeService();