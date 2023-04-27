import { createRouter, createWebHistory } from "vue-router";
import HomeSearch from "../views/HomeSearch";
import SearchHomebyCP from "../components/SearchHomebyCP";
import SearchHomebyCriteria from "../components/SearchHomebyCriteria";
import SearchHomebyBrand from "../components/SearchHomebyBrand";
import SearchHomebySC from "../components/SearchHomebySC";
import OwnerSearch from "../views/OwnerSearch";
import AgentSearch from "../views/AgentSearch";
import Transactions from "../views/Transactions";
import NewAgent from "../components/NewAgent";
import EditAgent from "../components/EditAgent";
// import DeleteAgent from '../components/DeleteAgent'
import NewHome from "../components/NewHome";
import EditHome from "../components/EditHome";
// import DeleteHome from '../components/DeleteHome'
import NewOwner from "../components/NewOwner";
import EditOwner from "../components/EditOwner";
// import DeleteOwner from '../components/DeleteOwner'
import HomeSell from "../components/HomeSell";
import HomePage from "../views/HomePage.vue"
// import App from "../App";

const routes = [
  {
    path: "/",
    name: "HomePage",
    component: HomePage,
  },
  {
    path: "/homesearch",
    name: "HomesearcH",
    component: HomeSearch,
  },
  {
    path: "/homesearch/searchbycityandprice",
    name: "HomesearchbyCP",
    component: SearchHomebyCP,
  },
  {
    path: "/homesearch/searchbycriteria",
    name: "HomesearchbyCriteria",
    component: SearchHomebyCriteria,
  },
  {
    path: "/homesearch/searchbysoldcount",
    name: "HomesearchbySC",
    component: SearchHomebySC,
  },
  {
    path: "/homesearch/searchbybybrand",
    name: "HomesearchbyBrand",
    component: SearchHomebyBrand,
  },
  {
    path: "/ownersearch",
    name: "OwnersearcH",
    component: OwnerSearch,
  },
  {
    path: "/agentsearch",
    name: "AgentsearcH",
    component: AgentSearch,
  },
  {
    path: "/transactions",
    name: "TransactionS",
    component: Transactions,
  },
  {
    path: "/transactions/newhome",
    name: "NewHome",
    component: NewHome,
  },
  {
    path: "/transactions/edithome",
    name: "EditHome",
    component: EditHome,
  },
  // {
  //   path: '/transactions/deletehome',
  //   name: 'DeleteHome',
  //   component: DeleteHome,
  // },
  {
    path: "/transactions/newagent",
    name: "NewAgent",
    component: NewAgent,
  },
  {
    path: "/transactions/editagent",
    name: "EditAgent",
    component: EditAgent,
  },
  // {
  //   path: '/transactions/deleteagent',
  //   name: 'DeleteAgent',
  //   component: DeleteAgent,
  // },
  {
    path: "/transactions/newowner",
    name: "NewOwner",
    component: NewOwner,
  },
  {
    path: "/transactions/editowner",
    name: "EditOwner",
    component: EditOwner,
  },
  // {
  //   path: '/transactions/deleteowner',
  //   name: 'DeleteOwner',
  //   component: DeleteOwner,
  // },
  {
    path: "/transactions/homesell",
    name: "HomeSell",
    component: HomeSell,
  },
  // {
  //   path: "/",
  //   name: "HomePage",
  //   component: App,
  // },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
