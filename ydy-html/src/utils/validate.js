/**
 * 校验类js
 */
/* 是否是登录邮箱*/
export function isLoginEmail(str) {
  const reg = /^[a-z0-9](?:[-_.+]?[a-z0-9]+)*@wz\.com$/i;
  return reg.test(str.trim());
}





