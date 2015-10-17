from django.shortcuts import render
from django.http import HttpResponse
from django.template import RequestContext, loader
from models import *
import random

def deleteall(request):
    Person.objects.all().delete()
    return HttpResponse("Ok")

def index(request):
    rand = random.random() * 100
    inserted = Person.objects.create(name = "name" + str(rand), age = rand)

    person = Person.objects.last()
    
    inserted.delete()
    
    return HttpResponse("Ok");
