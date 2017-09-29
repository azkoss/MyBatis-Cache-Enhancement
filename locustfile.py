from locust import HttpLocust, TaskSet, task

class MceTaskSet(TaskSet):

	@task(10)
	def queryProject(self):
		self.client.get('/project/list')

	@task(1)
	def insertProject(self):
		for x in range(1, 50):
			self.client.post('/project/save', {"name":"wanggang", "creator":x})

		self.client.post('/project/delete')


class MceLocust(HttpLocust):
	host="http://127.0.0.1:8080"
	task_set=MceTaskSet
	max_wait=15000
	min_wait=1500


