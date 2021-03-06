from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_cors import CORS
from flask_restx import Resource, Api

from apis.recommendation import recommendation
from apis.surveys import surveys

def create_app():
    app = Flask(__name__)
    
    app.config["SQLALCHEMY_DATABASE_URI"] = "mysql+pymysql://ubuntu:qwerty135!@j5a303.p.ssafy.io:3306/buki"
    app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
    # app.config['SQLALCHEMY_POOL_RECYCLE'] = 3600
    app.config['SQLALCHEMY_POOL_TIMEOUT'] = 180
    app.config['SQLALCHEMY_POOL_SIZE'] = 45
    app.config['SQLALCHEMY_MAX_OVERFLOW'] = 30

    db = SQLAlchemy(app)

    cors = CORS(app, resources={r"/*": {"origins": "*"}})
    
    app.register_blueprint(surveys, url_prefix="/data/api/surveys")
    app.register_blueprint(recommendation, url_prefix="/data/api/recommend")
    db.init_app(app)

    return app

if __name__ == "__main__":
    app = create_app()
    app.run(debug=True, host="0.0.0.0")