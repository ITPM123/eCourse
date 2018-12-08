import pathToRegexp from 'path-to-regexp'
import { getTotalMenu } from '../menu/menu'

// 提供给用户的平级配置形式
const initRoute={
  "/":{
      component:() =>import('../layout/BasicLayout.vue')
  },
  "/home":{
      component:() =>import('../views/home/home.vue')
  },
  "/setprofile":{
    component:() =>import('../views/setprofile/setprofile.vue')
},

"/manageacademic/academiclist":{
    component:() =>import('../views/manageacademic/list.vue')
},
"/manageacademic/addacademic":{
  component:() =>import('../views/manageacademic/add.vue')
},


"/manageteacher/teacherlist":{
    component:() =>import('../views/manageteacher/list.vue')
},
"/manageteacher/addteacher":{
  component:() =>import('../views/manageteacher/add.vue')
},

"/managestudent/studentlist":{
    component:() =>import('../views/managestudent/list.vue')
},
"/managestudent/addstudent":{
  component:() =>import('../views/managestudent/add.vue')
},

"/declaremanage/declarecourse":{
    component:() =>import('../views/declarecourse/list.vue')
},
"/declaremanage/createcourse":{
    component:() =>import('../views/declarecourse/create.vue')
},
"/declaremanage/detail":{
  component:() =>import('../views/declarecourse/detail.vue')
},

"/reviewcourse/list":{
  component:() =>import('../views/reviewcourse/list.vue')
},
"/reviewcourse/detail":{
  component:() =>import('../views/reviewcourse/detail.vue'),
  meta:{
    key:'reviewcourse'
  }
},
"/reviewcourse/result":{
  component:() =>import('../views/reviewcourse/result.vue'),
  meta:{
    key:'reviewcourse'
  }
},

"/schoolannouncement/viewannouncement":{
    component:() =>import('../views/schoolannouncement/viewannouncement.vue')
},
"/schoolannouncement/createannouncement":{
    component:() =>import('../views/schoolannouncement/createannouncement.vue')
},
"/course/mycourse":{
    component:() =>import('../views//course/mycourse.vue')
},
"/course/courseinfo":{
    component:() =>import('../views/course/courseinfo.vue')
},
"/course/courseannouncement":{
    component:() =>import('../views/course/courseannouncement.vue')
},
"/course/courseware":{
    component:() =>import('../views/course/courseware.vue')
},
"/course/homework/list":{
    component:() =>import('../views/course/teacherhomework/homeworklist.vue')
},
"/course/homework/review":{
  component:()=>import('../views/course/teacherhomework/homeworkreview.vue')
},
"/course/homework/data":{
  component:()=>import('../views/course/teacherhomework/homeworkdata.vue')
},
"/course/studenthomework":{
  component:()=>import('../views/course/studenthomework')
},
"/course/class":{
    component:() =>import('../views/course/courseclass.vue')
},
"/course/student":{
    component:() =>import('../views/course/coursestudent.vue'),
  // meta:{
  //   key:'course'
  // }
},

"/exception/404": {
    component: () => import('../views/Exception/404.vue'),
    name: '404'
},
}


function getFinishRoute() {
  const routerData = {}
  Object.keys(initRoute).forEach((path) => {
    const pathRegexp = pathToRegexp(path)
    const menuKey = Object.keys(menuData).find(key => pathRegexp.test(`${key}`))
    let menuItem = {}
    if (menuKey) {
      menuItem = menuData[menuKey]
    }
    let router = initRoute[path]
    router.meta = router.meta || {}
    router.meta.key = router.meta.key || menuItem.key
    router.name = router.name || menuItem.name
    routerData[path] = router
  })
  return routerData
}

function getFlatMenuData(menus) {
  let keys = {}
  menus.forEach(item => {
    if (item.children) {
      keys[item.path] = { ...item }
      keys = { ...keys, ...getFlatMenuData(item.children) }
    } else {
      keys[item.path] = { ...item }
    }
  })
  return keys
}

function getMatchRoutesPath(path, routerPaths) {
  let routes = routerPaths.filter(
    routePath => routePath.indexOf(path) === 0 && routePath !== path
  )
  return routes
}

function routerHandle(paths) {
  let cRouter = []
  let emRouter = []

  for (let path of paths) {
    if (path === '/' || emRouter.indexOf(path) > -1) continue

    const currentRoute = routerConfig[path]
    currentRoute.path = path
    const matchPaths = getMatchRoutesPath(path, paths)

    if (matchPaths && matchPaths.length > 0) {
      emRouter = emRouter.concat(matchPaths)
      currentRoute.children = routerHandle(matchPaths)
    }
    cRouter.push(currentRoute)
  }
  return cRouter
}

const initMenuData = getTotalMenu()
const menuData = getFlatMenuData(initMenuData) // 菜单树形结构转换成平级
const routerConfig = getFinishRoute()
console.log(routerConfig)

/**
 * 根据菜单取得重定向地址.
 */
const redirectData = []
const getRedirect = item => {
  if (item && item.children) {
    if (item.children[0] && item.children[0].path) {
      redirectData.push({
        path: `${item.path}`,
        redirect: `${item.children[0].path}`
      })
      item.children.forEach(children => {
        getRedirect(children)
      })
    }
  }
}
initMenuData.forEach(getRedirect)

export default [{
  ...routerConfig['/'],
  path: '/',
  redirect: '/home',
  children: redirectData.concat(routerHandle(Object.keys(routerConfig)))
}, {
  path: '*',
  redirect: '/exception/404',
}]
