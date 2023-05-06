import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from '../forms/LoginForm'
import HomeView from '../views/HomeView'
import Dashboard from '../components/Dashboard'
import RegistrationForm from '../forms/RegistrationForm'
import ProfileView from '../overviews/ProfileView'
import EditProfile from '../overviews/EditProfile'
import EntityOverview from '../overviews/EntityOverview'
import NoviSmestaj from '../components/NoviSmestaj'
import SmestajOverview from '../overviews/SmestajOverview'
import OwnerProfileOverview from '../overviews/OwnerProfileOverview'
import Reservation from '../components/Reservation'
import PregledRezervacija from '../overviews/PregledRezervacija'
import EditSmestaj from '../components/EditSmestaj'
import Moderacija from '../overviews/ModeracijaRezervacija'

const routes = [
  {
    path:`/moderate`,
    name:Moderacija,
    component:Moderacija
  },
  {
    path:`/editSmestaj/:id`,
    name: 'EditSmestaj',
    component:EditSmestaj
  },
  {
    path:`/reservationsOverview`,
    name:'PregledRezervacija',
    component:PregledRezervacija
  },
  {
    path:`/reservation/:id`,
    name:'Reservation',
    component:Reservation
  },
  {
    path:`/ownerProfile/:id`,
    name:'OwnerProfileOverview',
    component:OwnerProfileOverview
  },
  {
    path:`/pregled/:id`,
    name:'SmestajOverview',
    component:SmestajOverview
  },
  {
    path:`/create`,
    name:'NoviSmestaj',
    component:NoviSmestaj
  },
  {
    path:`/overview/:id`,
    name:'EntityOverview',
    component:EntityOverview
  },
  {
    path:`/edit`,
    name:'EditProfile',
    component:EditProfile
  },
  {
    path:`/register`,
    name:'RegistrationForm',
    component:RegistrationForm
  },
  {
    path:`/profile`,
    name:'ProfileView',
    component:ProfileView
  },
  {
    path:`/login`,
    name:'LoginForm',
    component:LoginForm
  },
  {
    path:`/dash`,
    name:'Dashboard',
    component:Dashboard
  },
  {
    path:`/`,
    name:'HomeView',
    component:HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
