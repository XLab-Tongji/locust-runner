from locust import HttpLocust, TaskSet, task
from random import choice

class UserBehavior(TaskSet):
    items = ["03fef6ac-1896-4ce8-bd69-b798f85c6e0b", "3395a43e-2d88-40de-b95f-e00e1502085b", "510a0d7e-8e83-4193-b483-e27e09ddc34d", "808a2de1-1aaa-4c25-a9b9-6612e8f29a38", "819e1fbf-8b7e-4f6d-811f-693534916a8b", "837ab141-399e-4c1f-9abc-bace40296bac", "a0a4f044-b040-410d-8ead-4de0446aec7e", "d3588630-ad8e-49df-bbd7-3167f7efb246",
             "zzz4f044-b040-410d-8ead-4de0446aec7e"]
    @task()
    def addtocart(self):
        someid = choice(UserBehavior.items)
        self.client.post("/cart", json={"id": someid},
                         headers={"Content-Type": "application/json; charset=UTF-8"})

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
