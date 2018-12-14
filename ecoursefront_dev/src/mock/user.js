import Mock from 'mockjs'

// 用户信息接口
// 当前用户拥有home页，demo页，analysis页和search页的权限。
// Mock.mock('/user/current', 'get', () => {
// return {
//     name: 'Jack Ma',
//     // authMenuKeys: ['home','setprofile','manageacademic']
//     authMenuKeys: ['home','setprofile','addacademic','academiclist','addteacher','teacherlist','studentlist','addstudent','reviewcourse','schoolannouncement','declarecourse','createcourse','viewannouncement','createannouncement','course']
//     // authMenuKeys: ['home','setprofile','declarecourse','schoolannouncement','course']
//     // authMenuKeys: ['home','setprofile','viewannouncement','createannouncement','course']
//   }
// })
