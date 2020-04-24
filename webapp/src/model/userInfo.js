import Vue from 'vue'

export default
{
  USER_ROLE_TEACHER:1,
  USER_ROLE_PARENTS:4,

  curUser:{},

  setCurUser(user)
  {
    var _this = this;
    _this.curUser = user;
  },
  isTeacher()
  {
    var _this = this;
    var roleIds = _this.curUser.Tea;
    if (roleIds.length == 0) return false;

    return true;
  },
  isParents()
  {
    var _this = this;
    var roleIds = _this.curUser.Tea;
    var role = _this.curUser.Stu;
    if(role.length > 0) return true;
    if (roleIds.length == 0) return false;

    return true;
  },
}
