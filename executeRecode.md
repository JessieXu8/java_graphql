1.查询全部link
{
  allLinks{
    url
    description
  }
}

2.新增link
mutation{
  createLink(url:"test url",description:"test description"){
    url,
    description
  }
}

3.新增user
mutation{
  createUser(
    name:"test name"
    authProvider:{
    	email:"test email"
        password:"test password"  
    }
  ){
  	id
    email
  }
}






