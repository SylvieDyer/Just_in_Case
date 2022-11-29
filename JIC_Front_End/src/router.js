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
    path: "/tutorials/:id",
    name: "tutorial-details",
    component: () => import("./components/Tutorial")
  },
  {
    path: "/add",
    name: "add",
    component: () => import("./components/AddTutorial")
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;