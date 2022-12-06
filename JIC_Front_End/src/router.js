import { createWebHistory, createRouter } from "vue-router";

const routes =  [
  // {
  //   path: "/login",
  //   alias: "/login",
  //   name: "login",
  //   component: () => import("./components/LogIn")
  // },
  {
    path:"/",
    name:"home",
    component: () => import("./App")
  },
  {
    path: "/feed/:caseID",
    alias: "/feed/:caseID",
    name: "feed",
    component: () => import("./components/Feed")
  },

  {
    path: "/buildinghub",
    name: "buildinghub",
    component: () => import("./components/AllBuildings")

  },
  {
    path: "/buildingname/:name",
    name: "buildingname",
    component: () => import("./components/Building")

  },
  {
    path: "/buildingid/:id",
    name: "buildingID",
    component: () => {import("./components/Building")}
  },

{
  path: "/buildinghub",
  name: "buildinghub",
  component: () => import("./components/AllBuildings")

},
{
  path: "/buildingname/:name",
  name: "buildingname",
  component: () => import("./components/Building")

},
{
  path: "/buildingid/:id",
  name: "buildingID",
  component: () => import("./components/Building")
},
{
  path: "/facility/:id",
  name: "facilityByID",
  component: () => import("./components/Building")
},
{
  path: "/createFacility/:id",
  name: "createFacilityByID",
  component: () => import("./components/Building")
},



];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;