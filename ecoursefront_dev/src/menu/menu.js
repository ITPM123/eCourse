import { isUrl } from '../utils/index'


const initMenuData = [
  {
      name:'主页',
      key:'home',
      icon:'table',
      path:'home'
  },
  {
      name:'个人资料',
      key:'setprofile',
      icon:'table',
      path:'setprofile'
  },
  {
      name:'教员管理',
      key:'manageacademic',
      icon:'table',
      path:'manageacademic',
      children:[
        {
            name:'教员列表',
            key:'academiclist',
            path:'academiclist'
        },
        {
            name:'添加教员',
            key:'addacademic',
            path:'addacademic'
        }
    ]
  },
  {
      name:'教师管理',
      key:'manageteacher',
      icon:'table',
      path:'manageteacher',
      children:[
        {
            name:'教师列表',
            key:'teacherlist',
            path:'teacherlist'
        },
        {
            name:'添加教师',
            key:'addteacher',
            path:'addteacher'
        }
    ]
  },
  {
      name:'学生管理',
      key:'managestudent',
      icon:'table',
      path:'managestudent',
      children:[
        {
            name:'学生列表',
            key:'studentlist',
            path:'studentlist'
        },
        {
            name:'添加学生',
            key:'addstudent',
            path:'addstudent'
        }
    ]
  },
  {
      name:'申报管理',
    //   key:'declaremanege',
      icon:'table',
      path:'declaremanage',
      children:[
        {
            name:'申报列表',
            key:'declarecourse',
            path:'declarecourse'
        },
        {
            name:'申报课程',
            key:'createcourse',
            path:'createcourse'
        }
    ]
  },
  {
      name:'审核管理',
      key:'reviewcourse',
      icon:'table',
      path:'reviewcourse/list'
  },
  {
      name:'教务公告',
      // key:'schoolannouncement',
      icon:'table',
      path:'schoolannouncement',
      children:[
          {
              name:'公告列表',
              key:'viewannouncement',
              path:'viewannouncement'
          },
          {
              name:'发布公告',
              key:'createannouncement',
              path:'createannouncement'
          }
      ]
  },
  {
      name:'课程管理',
      key:'course',
      icon:'table',
      path:'course',
      children:[
          {
              name:'我的课程',
              path:'mycourse'
          }, 
          {
              name:'课程信息',
              path:'courseinfo'
          }, 
          {
              name:'课程公告',
              path:'courseannouncement'
          }, 
          {
              name:'课程资源',
              path:'courseware'
          }, 
          {
              name:'作业管理',
              path:'homework/list'
          }, 
          {
              name:'课程作业',
              path:'studenthomework'
          },
          {
              name:'学生管理',
              path:'class'
          }
      ]
  }
]

function formatter(data, parentPath = '/', parentKey) {
  return data.map(item => {
    let { path, key } = item
    if (!isUrl(path)) {
      path = parentPath + item.path
    }
    const currentKey = key || parentKey
    let result = {
      ...item,
      path,
      key: currentKey
    }

    if (item.children) {
      result.children = formatter(item.children, `${parentPath}${item.path}/`, currentKey)
    }

    return result
  })
}

/**
 * 根据菜单权限码生成最终的树形菜单结构
 * @param {Array} menu 用户定义的全量菜单树状结构
 * @param {Array} authMenuKeys 用户信息中拿到的菜单权限码
 * @return {Array} 鉴权后的树形菜单
 */
function getAuthMenuData(menu, authMenuKeys) { // eslint-disable-line
  return menu.filter((item) => {
    if (item.children && item.children.length > 0) {
      item.children = getAuthMenuData(item.children, authMenuKeys)
      return item.children.length > 0
    }
    return authMenuKeys.indexOf(item.key) > -1
  })
}

/**
 * 获取鉴权后的菜单结构
 * @param {Array} authMenuKeys 用户信息中拿到的菜单权限码
 * @return {Array} 鉴权后的树形菜单
 */
export const getMenuData = (authMenuKeys = []) => {
  return getAuthMenuData(formatter(initMenuData), authMenuKeys)
}

/**
 * 获取全量菜单结构
 * @return {Array} 全量菜单
 */
export const getTotalMenu = () => {
  console.log(formatter(initMenuData))
  return formatter(initMenuData)
}
