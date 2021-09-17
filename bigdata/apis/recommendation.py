from flask import Blueprint, make_response, jsonify
from flask_restx import Api, Resource

from models.HobbyClass import HobbyClass

recommendation = Blueprint("recommendation", __name__)
api = Api(recommendation)

@recommendation.route("/<int:id>", methods=["GET"])
def recomm(id):
  res = HobbyClass.query.filter_by(id = [id]).first()
  return HobbyClass.as_dict(res)