import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from '../forms/LoginForm'
import HomeView from '../views/HomeView'
import Dashboard from '../components/Dashboard'
import RegistrationForm from '../forms/RegistrationForm'
import ProfileView from '../overviews/ProfileView'
import EditProfile from '../overviews/EditProfile'
import EntityOverview from '../overviews/EntityOverview'
import NoviLet from '../components/NoviLet'
import About from '../views/AboutView'
import FlightsView from '../overviews/FlightsView'
import FlightOverview from '../overviews/FlightOverview'
import Pretraga from '../components/Pretraga'
import FlightsOverviewAdmin from '../overviews/FlightsOverviewAdmin'

const routes = [
  {
    path:`/adminFlights`,
    name:'FlightsOverviewAdmin',
    component:FlightsOverviewAdmin
  },
  {
    path:`/create`,
    name:'NoviLet',
    component:NoviLet
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
    component:About
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:`/userFlights`,
    name:'FlightsView',
    component:FlightsView
  },
  {
    path:`/flight/:id`,
    name:'FlightOverview',
    component:FlightOverview
  },
  {
    path:'/search',
    name:'search',
    component:Pretraga
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router