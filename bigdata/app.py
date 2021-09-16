from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_cors import CORS
from flask_restx import Resource, Api

from apis.recommendation import recommendation

def create_app():
    app = Flask(__name__)
    
    app.config["SQLALCHEMY_DATABASE_URI"] = "mysql+pymysql://ubuntu:qwerty135!@j5a303.p.ssafy.io:3306/buki"
    db = SQLAlchemy(app, session_options={'autocommit': True})

    cors = CORS(app, resources={r"/*": {"origins": "*"}})
    app.register_blueprint(recommendation, url_prefix="/data/api/recommend")
    
    return app

if __name__ == "__main__":
    app = create_app()
    app.run(debug=True, host="0.0.0.0")