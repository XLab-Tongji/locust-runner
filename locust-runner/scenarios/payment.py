from locust import HttpLocust, TaskSet, task, seq_task
import random

class UserBehavior(TaskSet):
    items = ["03fef6ac-1896-4ce8-bd69-b798f85c6e0b", "3395a43e-2d88-40de-b95f-e00e1502085b", "510a0d7e-8e83-4193-b483-e27e09ddc34d", "808a2de1-1aaa-4c25-a9b9-6612e8f29a38", "819e1fbf-8b7e-4f6d-811f-693534916a8b", "837ab141-399e-4c1f-9abc-bace40296bac", "a0a4f044-b040-410d-8ead-4de0446aec7e", "d3588630-ad8e-49df-bbd7-3167f7efb246",
             "zzz4f044-b040-410d-8ead-4de0446aec7e"]
             
    def on_start(self):
        """ on_start is called when a Locust start before any task is scheduled """
        self.login()
    
    def on_stop(self):
        """ on_stop is called when the TaskSet is stopping """
        self.logout()
   
    def login(self):
        rand = random.randrange(1000000000, 9999999999, 1)
        self.client.post("/register", json = {"username":str(rand),"password":str(rand),"email":"","firstName":"","lastName":""}, \
                         headers = {"Content-Type":"application/json; charset=UTF-8"})
        self.client.get("/login", auth=(str(rand),str(rand)))
    
    def logout(self):
        self.client.cookies.clear()
	
    @seq_task(1)
    def filladdress(self):
        self.client.post("/addresses", json = {"number":"","street":"","city":"","postcode":"","country":""}, \
                         headers = {"Content-Type":"application/json; charset=UTF-8"})
    
    @seq_task(2)
    def fillcreditcard(self):
        self.client.post("/cards", json = {"longNum":"1234","expires":"","ccv":""}, \
                         headers = {"Content-Type":"application/json; charset=UTF-8"})
    
    @seq_task(3)
    def addtocart(self):
        someid = random.choice(UserBehavior.items)
        self.client.post("/cart", json={"id": someid},
                         headers={"Content-Type": "application/json; charset=UTF-8"})
    
    @seq_task(4)
    def payment(self):
        self.client.post("/orders")
	
class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
