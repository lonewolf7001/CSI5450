import { createRouter, createWebHistory } from 'vue-router'
import HouseSearch from '../views/HouseSearch'
import OwnerSearch from '../views/OwnerSearch'
import AgentSearch from '../views/AgentSearch'
import Transactions from '../views/Transactions'
const routes = [
  {
    path: '/HouseSearch',
    name: 'HousesearcH',
    component: HouseSearch,
  },
  {
    path: '/ownersearch',
    name: 'OwnersearcH',
    component: OwnerSearch,
  },
  {
    path: '/agentsearch',
    name: 'AgentsearcH',
    component: AgentSearch,
  },
  {
    path: '/transactions',
    name: 'TransactionS',
    component: Transactions,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router